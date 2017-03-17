(ns codejam.last-word)
;;http://code.google.com/codejam/contest/4304486/dashboard#s=p0

(defn last-word [s]
  (reduce #(if (< (int %2) (int (first %1))) (str %1 %2) (str %2 %1)) (str (first s)) (next s)))
(last-word "ABCABCABC")

(defn -solve []
  (let [T (Integer/parseInt (read-line))]
    (dotimes [t T]
      (println (str "Case #" (inc t) ": " (last-word (read-line)))))))


;;http://code.google.com/codejam/contest/4304486/dashboard#s=p1&a=1
(defn missing-seq [ar]
  (sort (map first (filter #(odd? (second %)) (frequencies (flatten ar))))))


(defn -solve1 []
  (let [T (Integer/parseInt (read-line))]
    (dotimes [t T]
      (let [n (dec (* 2 (Integer/parseInt (read-line))))]
        (loop [i 0 ar []]
          #_(println "i" i "n" n "t" t "ar" ar)
          (if (= i n) (println (str "Case #" (inc t) ": " (clojure.string/join " " (missing-seq ar))))
              (recur (inc i ) (conj ar (map #(Integer/parseInt %) (clojure.string/split (read-line) #"\s+" )))))
          )
        ))))
