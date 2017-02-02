(ns hackerearth.practice
  (:use clojure.set))

(defn -prime? [n]
  (if (even? n) false
                (let [root (num (Math/sqrt n))]
                  (loop [i 3]
                    (if (> i root) true
                                   (if (zero? (mod n i)) false
                                                         (recur (+ i 2))))))))

(def prime? (memoize -prime?))


(defn -numfac [n]
  "number of factors of n."
  (if (or (= n 2) (= n 1)) n
                           (let [sq (inc (/ n 2))]
                             (loop [n1 n k 2 v []]
                               (if (or (= n1 1) (>= k sq))
                                 (reduce #(* %1 (inc %2)) 1 (map second (frequencies v)))
                                 (if (prime? n1) (recur 1 n1 (conj v n1)) (if (zero? (rem n1 k)) (recur (/ n1 k) k (conj v k)) (recur n1 (if (= k 2) 3 (+ 2 k)) v))))))))

(def num-fac (memoize -numfac))

(defn gcd
  "(gcd a b) computes the greatest common divisor of a and b."
  [a b]
  (if (zero? b)
    a
    (recur b (mod a b))))


(defn -main  []
  (let [s-ar (clojure.string/split (read-line) #" ") a (Long/parseLong (first s-ar)) b (Long/parseLong (second s-ar))]
    (println (num-fac (gcd a b)))))

(-main)