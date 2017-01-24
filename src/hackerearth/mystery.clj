;;https://www.hackerearth.com/practice/math/number-theory/basic-number-theory-1/practice-problems/algorithm/mystery-20/

(ns hackerearth.mystery) 

(defn -prime? [n]
  (if (even? n) false
      (let [root (num (Math/sqrt n))]
        (loop [i 3]
          (if (> i root) true
            (if (zero? (mod n i)) false
              (recur (+ i 2))))))))

(def prime? (memoize -prime?))


(defn -solve [n]
  "number of factors of n."
  (if (or (= n 2) (= n 1)) n 
    (let [sq (inc (/ n 2))]
      (loop [n1 n k 2 v []]
        (if (or (= n1 1) (>= k sq)) 
          (reduce #(* %1 (inc %2)) 1 (map second (frequencies v)))
          (if (prime? n1) (recur 1 n1 (conj v n1)) (if (zero? (rem n1 k)) (recur (/ n1 k) k (conj v k)) (recur n1 (if (= k 2) 3 (+ 2 k)) v))))))))

(def solve (memoize -solve))

(defn -main []
  (let [T (Long/parseLong (read-line))]
    (let [s (read-line)]
      (if (.contains s " ") 
        (println (clojure.string/join "\n" (map #(solve (Long/parseLong %)) (clojure.string/split s #" "))))
        (println (str (solve (Long/parseLong s)) "\n" (clojure.string/join "\n" (map solve (for [t (range (dec T))] (Long/parseLong (read-line)))))))))))
        

