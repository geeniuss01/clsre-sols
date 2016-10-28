(ns clsre.core
  (:use [codejam.counting-sheep :as sheep]
          [fourclojure.fourclj :as c4])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
  (println (c4/to-roman (sheep/read-int)))
