(ns giants-shoulders.repl
  (:require [nrepl.server :as nrepl]
            [cider.nrepl :as cider]
            [malli.dev :as malli-dev]
            [malli.dev.pretty :as malli-pretty]
            [taoensso.telemere :as te]
            [mount.core :as mount]
            [rebel-readline.core :as rr]
            [rebel-readline.clojure.line-reader :as rr-clr]
            [rebel-readline.clojure.service.local :as rr-csl]
            [rebel-readline.clojure.main :as rr-cm]
            [clojure.main :as clj-main]))

(mount/defstate system-status-logger
  :start (te/log! :info "Mount system started")
  :stop (te/log! :info "Mount system stopped"))

(defn start!
  "Start a development REPL."
  [_params]
  (te/log! :info "Starting malli dev instrumentation")
  (malli-dev/start! {:report (malli-pretty/thrower)})

  (te/log! :info "Starting mount system")
  (mount/start)

  (te/log! :info "Starting nREPL server")
  (let [{:keys [port] :as _server} (nrepl/start-server :handler cider/cider-nrepl-handler)]
    (te/log!
     {:level :info
      :msg "nREPL server started"
      :data {:port port}})
    (te/log! :info "Writing port to .nrepl-port")
    (spit ".nrepl-port" port))

  (te/log! :info "Starting interactive REPL")
  (rr/with-line-reader
    (rr-clr/create (rr-csl/create))
    (clj-main/repl
     :prompt (fn [])
     :read (rr-cm/create-repl-read)))

  (te/log! :info "Shutting down")

  (te/log! :info "Stopping mount system")
  (mount/stop)

  (shutdown-agents)
  (System/exit 0))
