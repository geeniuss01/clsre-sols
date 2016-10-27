(ns funcs)

(def __ (fn [sq]
          (reduce (fn [new old]
                    (conj new old)) '() sq)))
;
;(println (rev [1 2 3 4 5]))
;(println (rev '(1 2 3 4 5)))
;(println (rev [[1 2][3 4][5 6]]))
;(println (rev (sorted-set 5 7 2 7)))
;
;(println (= (__ [1 2 3 4 5]) [5 4 3 2 1]))
;
;(println (= (__ (sorted-set 5 7 2 7)) '(7 5 2)))
;
;(println (= (__ [[1 2][3 4][5 6]]) [[5 6][3 4][1 2]]))


(def isPerfect? (fn [num]
                  (= num (apply + (filter #(= 0 (rem num %)) (range 1 (inc (/ num 2))))))))

;(println (isPerfect? 6))


(def count-occ (fn [sq]
                 " a function which returns a map containing the number of occurences of each distinct item in a sequence."
                 (reduce #(assoc %1 %2 (inc (%1 %2 0))) {} sq)))

; (println (count-occ [1 1 2 3 2 1 1]))

(def inc-ssq (fn
               [osq]
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

;(println)
(println (= (inc-ssq [1 0 1 2 3 0 4 5]) [0 1 2 3]))
;(println (inc-ssq [2 3 3 4 5]))
;(println (inc-ssq [7 6 5]))
