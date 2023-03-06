(ns giants-shoulders.repl
  (:require [nrepl.server :as nrepl]
            [cider.nrepl :as cider]
            [malli.core :as m]
            [malli.dev :as malli-dev]
            [malli.dev.pretty :as malli-pretty]
            [taoensso.timbre :as log]
            [portal.api :as portal]
            [mount.core :as mount]
            [rebel-readline.core :as rr]
            [rebel-readline.clojure.line-reader :as rr-clr]
            [rebel-readline.clojure.service.local :as rr-csl]
            [rebel-readline.clojure.main :as rr-cm]
            [clojure.main :as clj-main]))

(mount/defstate system-status-logger
  :start (log/info "Mount system started")
  :stop (log/info "Mount system stopped"))

(defn- add
  "If you have LSP configured correctly you should see a type error / warning if you try to type (add :foo 10) inside this buffer."
  [a b]
  (+ a b))
(m/=> add [:=> [:cat number? number?] number?])

(defn start!
  "Start a development REPL, intended to be invoked from ./scripts/repl"
  [{:keys [portal]}]
  (log/info "Starting malli dev instrumentation")
  (malli-dev/start! {:report (malli-pretty/thrower)})

  (log/info "Starting mount system")
  (mount/start)

  (log/info "Starting nREPL server")
  (let [{:keys [port] :as _server} (nrepl/start-server :handler cider/cider-nrepl-handler)]
    (log/info "nREPL server started on port" port)
    (log/info "Writing port to .nrepl-port")
    (spit ".nrepl-port" port))

  (when portal
    (log/info "Opening portal, use (tap> ...) to inspect values")
    (portal/open)
    (add-tap #'portal/submit))

  (log/info "Starting interactive REPL")
  (rr/with-line-reader
    (rr-clr/create (rr-csl/create))
    (clj-main/repl
     :prompt (fn [])
     :read (rr-cm/create-repl-read)))

  (log/info "Shutting down")

  (when portal
    (log/info "Closing portal")
    (portal/close))

  (log/info "Stopping mount system")
  (mount/stop)

  (shutdown-agents)
  (System/exit 0))
