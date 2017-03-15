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
