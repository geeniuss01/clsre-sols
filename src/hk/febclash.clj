
(ns hk.febclash)

(def p 1000000007N)

(defn s [a]
  (rem (dec (reduce *'  1 a)) p))

(defn -main [] 
  (let [s1 (read-line)]
    (println (str (s (map (fn [n] (rem (inc (bigint n)) p)) (clojure.string/split (read-line) #"\s+")))))))

;(-main)

(def k 10)
(def n1 [ 6 8 14])
(defn mdk [a b] 
  (rem (apply * a) b))
;;(mdk n1 k)
;;(mdk (map #(rem % k) n1) k )



