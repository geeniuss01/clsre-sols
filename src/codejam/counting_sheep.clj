(ns codejam.counting-sheep)

; counting sheep https://code.google.com/codejam/contest/6254486/dashboard
(defn read-int []
  (Integer/parseInt (read-line)))

(defn count-till-all-digits [n]
  "returns a number at which point all digits are seen"
  (if (= n 0) "INSOMNIA" (loop [num n mul 1 tot #{}]
                           (let [mul1 (inc mul)
                                 newtot (apply conj tot (str num))]
                             (if (= (count newtot) 10) num (recur (* n mul1) mul1 newtot)))
                           ))
  )

(defn solve []
  (let [T (read-int)]
    (doseq [t (range T)]
      (println (str "Case #" (inc t) ": " (count-till-all-digits (read-int))))
      )
    )
  )

(solve
  (1))