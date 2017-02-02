(ns hackerearth.fizzbuzz)
;;https://www.hackerearth.com/challenge/test/practice-challenge/algorithm/eab008eed22dc06e6280a2b6dbb042e0/
(defn fbz [n3 n5 n15 d]
  (lazy-seq (cons (cond
               (zero? n15) "FizzBuzz"
               (zero? n5) "Buzz"
               (zero? n3) "Fizz"
               :else d) (fbz (rem (inc n3) 3) (rem (inc n5) 5) (rem (inc n15) 15) (inc d))))
  )

(defn solve [k]
  (clojure.string/join "\n" (take k (fbz 1 1 1 1))))

(defn -main []
  (let [T (Long/parseLong (read-line))]
    (let [s (read-line) ts (clojure.string/split s #" ")]
      (println (clojure.string/join "\n" (map #(solve (Long/parseLong %)) ts))))))

(-main)



;; testing
(def k 1)
;;(println (clojure.string/join "\n" (take 20 (fbz (rem k 3) (rem k 5) (rem k 15) k))))
;;(println (solve 17))

(defn fbz1 [n3 n5 n15 d]
  (cons (cond
          (zero? n15) "fizzbuzz"
          (zero? n5) "buzz"
          (zero? n3) "fizz"
          :else d) (lazy-seq (fbz1 (rem (inc n3) 3) (rem (inc n5) 5) (rem (inc n15) 15) (inc d))))
  )
