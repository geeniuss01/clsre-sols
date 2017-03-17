(ns hk.wc30)


;;https://www.hackerrank.com/contests/w30/challenges/melodious-password
(def vowels #{\a \e \i \o \u})
(def consonants #{\b \c \d  \f \g \h  \j \k \l \m \n \p \q \r \s \t  \v \w \x \z})

(defn nxt [w]
  (let [s (if (contains? vowels (last w)) consonants vowels)]
    (map #(str w %) s)))

(defn all-comb [n]
  (loop [words (into #{}(apply conj (map str vowels) (map str consonants))) n1 1]
    (if (= n1 n) words
        (recur (flatten (map nxt words)) (inc n1)))))


;;https://www.hackerrank.com/contests/w30/challenges/poles

;; MEMOIZE | SORT

(defn _cost-to-group [v]
  (let [[dest-alt dest-wt] (first v)]
    (reduce #(+ %1 (* (second %2)(- (first %2) dest-alt))) 0 v)))

(def cost-to-group  (memoize _cost-to-group))

(defn _cal-for-selection [v]
  (reduce + (map cost-to-group v) ))

(def cal-for-selection (memoize _cal-for-selection))

(def rc (memoize (fn [v grp-cnt]
           #_(println "rc >>>>" v grp-cnt)
                   (cond (= 1 grp-cnt) (cost-to-group v)
                         (= (count v) grp-cnt) 0
                         (> grp-cnt (count v)) (Integer/MAX_VALUE)
                         :else (apply min (map #(+ (cost-to-group (first %)) (rc (second %) (dec grp-cnt)))
                                       (map #(split-at % v) (range 1 (count v)))))))))

(sort-by first >  ll)

(def ll [[20 3] [30 1] [40 2]])
;(_rc [[20 1] [30 1] [40 1]] 1)
(rc [[10 15] [12 17] [16 18] [18 13] [30 10] [32 1]] 6)
(rc  '([12 17] [16 18] [18 13] [30 10] [32 1]) 4)
;(_cost-to-group ['(20 3)])
                                        ;(cal-for-selection ['([20 3]) '([30 1] [40 2]) '([30 1] [40 3])])

(println (map #(split-at % ll) (range 1 (count ll))))
(min (map #(+ (cost-to-group (first %)) (count (second %))) (map #(split-at % ll) (range 1 (count ll)))))
(cost-to-group [[20 3]])
