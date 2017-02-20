(ns hackerearth.sumofnum)
;;https://www.hackerearth.com/challenge/test/practice-challenge/algorithm/55d723d517919c211ca6e0b5eee0bac6/
(defn -main []
  (let [T (Long/parseLong (read-line))]
    (dotimes [t T] (let [s (read-line) ts (clojure.string/split s #" ")]
       (println (reduce #(+ %1 (Long/parseLong %2)) 0 ts))))))

;(-main)