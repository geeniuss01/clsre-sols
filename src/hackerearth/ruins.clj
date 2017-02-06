(ns hackerearth.ruins)
;; https://www.hackerearth.com/practice/algorithms/greedy/basics-of-greedy-algorithms/practice-problems/algorithm/exploring-ruins/

(defn choose [s s1 s2]
  (if (= s2 \?)
    (let [p (last s1) n (nth s (inc (count s1)) nil)]
     (if (or (= \a p) (= \a n)) \b \a)) s2))

(defn -main []
  (let [s (read-line)]
    (println (clojure.string/join ""
                          (reduce
                            #(conj %1 (choose s %1 %2)) [] (vec s))))))
(-main)