(ns fourclojure.funcs)

(def __ (fn [sq]
          (reduce (fn [new old]
                    (conj new old)) '() sq)))

(def isPerfect? (fn [num]
                  (= num (apply + (filter #(= 0 (rem num %)) (range 1 (inc (/ num 2))))))))

;(println (isPerfect? 6))


(def count-occ (fn [sq]
                 " a function which returns a map containing the number of occurences of each distinct item in a sequence."
                 (reduce #(assoc %1 %2 (inc (%1 %2 0))) {} sq)))


(def inc-ssq (fn
               [osq]
               "increasing sub-sequence"
               (loop [lngst [] sq osq]
                 (if (= (first sq) nil)
                   (if (< (count lngst) 2) [] lngst)
                   (let [cur-seq (filter #(not= nil %) (reduce #(if (= nil (last %1)) %1 (if (< (last %1) %2) (conj %1 %2) (conj %1 nil))) [(first sq)] (rest sq)))]
                     (if (> (count cur-seq) (count lngst)) (recur cur-seq (next sq)) (recur lngst (next sq)))
                     ))
                 )
               ))

(defn ms-at
  "max sq at 0"
  [sq]
  (filter #(not= nil %) sq))

(defn maxx
  "max using loop"
  [sq]
  (loop [m -1 remn sq]
    (if (empty? remn) (if (< (count m) 2) [] m) (recur (max (first remn) m) (next remn)))
    ))

(println (ms-at [1 2 nil]))
(println (= (inc-ssq [1 0 1 2 3 0 4 5]) [0 1 2 3]))
