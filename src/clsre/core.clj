(ns clsre.core
  (:use [codejam.counting-sheep :as sheep]
        [fourclojure.fourclj :as c4])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (cond
    (not= 1 (count args)) (println "need exactly one number arg")
    (not (number? (first args))) (println "enter only numbers")
    (= (first args) 1) (println "Hello, World!")
    :else (println "need one of these\n1  : hello world \n!1 : nothing"))
  )