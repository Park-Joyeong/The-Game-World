(ns gameworld.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [compojure.core :refer :all]
            [compojure.route :as route])
  (:gen-class))

(defroutes app
  (GET "/" [] "<h1>Hello World!</h1>")
  (GET "/friends" [] "<h1>There are no friends yet :(</h1>"))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (run-jetty app {:port 80}))
