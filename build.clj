(ns build
  (:require [clojure.tools.build.api :as b]
            [deps-deploy.deps-deploy :as dd]))

(def lib 'uk.me.oli/clojure-template)

;; TODO Use the GitHub rev / tag
;; https://github.com/jlesquembre/clojars-publish-action/blob/89a4eb7bdbe1270621e6643250afce152701699e/src/entrypoint.clj#L41-L47
(def version "0.0.1") ; (b/git-count-revs nil)

(def class-dir "target/classes")
(def jar-file (format "target/%s-%s.jar" (name lib) version))

(def pom-template
  [[:description "Your description"]
   [:url "https://github.com/Olical/clojure-template"]
   [:licenses
    [:license
     [:name "Unlicense"]
     [:url "https://unlicense.org/"]]]
   [:developers
    [:developer
     [:name "Oliver Caldwell"]]]
   [:scm
    [:url "https://github.com/Olical/clojure-template"]
    [:connection "scm:git:https://github.com/Olical/clojure-template.git"]
    [:developerConnection "scm:git:ssh://git@github.com:Olical/clojure-template.git"]
    [:tag (str "v" version)]]])

(def basis (delay (b/create-basis {:project "deps.edn"})))

(defn clean [_]
  (b/delete {:path "target"}))

(defn jar [_]
  (b/write-pom {:class-dir class-dir
                :lib lib
                :version version
                :basis @basis
                :pom-data pom-template
                :src-dirs ["src"]})

  (b/copy-dir {:src-dirs ["src" "resources"]
               :target-dir class-dir})

  (b/jar {:class-dir class-dir
          :jar-file jar-file}))

(defn deploy "Deploy the JAR to Clojars." [opts]
  (dd/deploy {:installer :remote :artifact (b/resolve-path jar-file)
              :pom-file (b/pom-path
                         {:lib lib
                          :class-dir class-dir})})
  opts)
