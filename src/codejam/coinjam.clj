(ns codejam.coinjam)

(defn all-bases[s]
  (loop [next-base 2 vals []]
    (if (= next-base 10) vals
        (recur (inc next-base) (conj vals (Long/parseLong s next-base) )))))

(defn prime? [n]
  (if (even? n) 2
      (let [root (num (int (Math/sqrt n)))]
        (loop [i 3]
          (if (> i root) true
              (if (zero? (mod n i)) i
                  (recur (+ i 2))))))))


(defn fmt [n]
  (conj (map #(if (number? (prime? %)) %  (str % "<" (prime? %) ">"))(all-bases n)) n))

(defn nextlevel [ar] (let [ ar1 (map #(str "0" %) ar) ar2 (map #(str "1" %)
                                                       ar)] (concat ar1 ar2)))
(defn binary-of-len [n]
  (nth (iterate nextlevel ["0" "1"]) (dec n)))


(def p (->> (binary-of-len 4)
      (map #(str "1" % "1"))
      (map fmt)
      (map println)))
