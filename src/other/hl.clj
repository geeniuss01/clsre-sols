(ns other.hl)
(defn fact-loop [n]
  (loop [current n fact 1]
    (if (= current 1)
      fact
      (recur (dec current) (* fact current)))))

;; loops over with a single vector
(defn fibo1 [n]
  (loop [ls [1 1]]
    (let [len (count ls) x (last ls) y (ls (- len 2))]
      (if (= n len)
        ls
        (recur (conj ls (+' x y))))
      )
    )
  )

(defn say [n1]
  (fn [n2] (str "hello " n2 " from " n1)))
(def sayM (say "samen"))
(def n 15)
(println (fibo1 n))
(print (clojure.string/join "\n" (map sayM (vector "sa" "ri" "ga" "ma"))))