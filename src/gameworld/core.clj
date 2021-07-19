(ns gameworld.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [compojure.core :refer [GET defroutes]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [config.core :refer [env]]
            [rum.core :refer [defc render-static-markup]]
            [next.jdbc :as jdbc])
  (:gen-class))

(defc template [headline component]
  [:div {:id "main-div"
         :class "main-page-div"}
   [:h1 headline]
   [:ul {:class "nav"}
    [:li [:a {:href "/"} "Home"]]
    [:li [:a {:href "/friends"} "Friends"]]]
   (component)])

(defc main-page [req]
  [:p (str req)])

(defc friends-page []
  [:p "This is the firends page, it is empty, yet"])

(defroutes app
  (GET "/" [req] (render-static-markup (template "Hello, World!" (main-page {:request req}))))
  (GET "/friends" [] (render-static-markup (template "No friends, yet :(" (friends-page)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [datasource (jdbc/get-datasource (:db env))]
    (run-jetty (wrap-defaults app site-defaults) {:port (:port env)})))
