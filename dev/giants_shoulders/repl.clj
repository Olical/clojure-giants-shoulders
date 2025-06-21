(ns giants-shoulders.repl
  (:require [nrepl.server :as nrepl]
            [cider.nrepl :as cider]
            [taoensso.telemere :as te]
            [rebel-readline.core :as rr]
            [rebel-readline.clojure.line-reader :as rr-clr]
            [rebel-readline.clojure.service.local :as rr-csl]
            [rebel-readline.clojure.main :as rr-cm]
            [clojure.main :as clj-main]))

(defn start!
  "Start a development REPL."
  [_params]
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

  (shutdown-agents)
  (System/exit 0))
