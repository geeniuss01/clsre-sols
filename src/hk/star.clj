(ns hk.star)

(defn rd []
  (Double/parseDouble (read-line)))

(def arth (fn [] 
           (let [meal (rd) tip (rd) tax (rd)]
             (println "The total meal cost is" (Math/round (+  meal (/ (* meal tip) 100) (/ (* meal tax) 100) )) "dollars."))))


;; https://www.hackerrank.com/challenges/utopian-tree
(def uto 
  (fn [N] 
    (loop [n 0 h 1 spring true]
;;      (println n h spring)
      (if (>= n N) h 
          (recur (inc n) (if spring (* h 2) (inc h)) (not spring))))))
;;(uto 4)



;; https://www.hackerrank.com/challenges/circular-array-rotation
(defn rotate [col]
  (conj (take (dec (count col)) col) (last col)))

;;(nth (iterate rotate [1 2 3 4]) 2)

;; anothe way
(def p [1 3 4 5 6])
(nth p (+ (count p) (- 1 2)))

;; factorial https://www.hackerrank.com/challenges/extra-long-factorials
(defn fact [n]
  (reduce *' (range 1 (inc n))))

;;(fact 25)

;; https://www.hackerrank.com/challenges/beautiful-binary-string
(defn beau [s1]
  (loop [n 0 s s1]
    (cond
     (empty? s) n
     (.startsWith s "01010") (recur (inc n) (.substring s 4))
     (.startsWith s "010") (recur (inc n) (.substring s 3))
     :else (recur n (.substring s 1)))))
     
    


;;(beau "010")

;; https://www.hackerrank.com/challenges/funny-string

(defn is-funny? [s]
  (loop [i 0 j (dec (count s))]
    (cond 
     (= 2 (count s)) "Funny"
     (< (- j i) 2) "Funny"
     :else (let [i1 (int (nth s i)) i2 (int (nth s (inc i))) j1 (int (nth s j)) j2 (int (nth s (dec j)))] 
             (if (= (Math/abs (- i1 i2)) (Math/abs (- j1 j2)))
               (recur (inc i) (dec j))
               "Not funny")))))
     

(def ip (fn []  (let [N (Integer/parseInt (read-line))]
                 (dotimes [n N] 
                   (println (is-funny? (read-line)))))))

;;(is-funny? "ab")
;;(is-funny? "bcxz")


;;https://www.hackerrank.com/challenges/countingsort1
(defn countt []
  (let [N (read-line) s (read-line) ar (map #(Integer/parseInt %) (clojure.string/split s #" "))
        f (frequencies ar)]
    (dotimes [n 100] (print (f (inc n) 0) ""))))
        
  
;;challenges/pangrams
(defn pangram? [s]
  #_(println (sort (set s)))
  (.contains (clojure.string/join "" (sort (set (.toLowerCase s)))) "abcdefghijklmnopqrstuvwxyz"))

(defn pangram-test [] 
  (let [s (read-line)]
    (println (if (pangram? s) "pangram" "not pangram"))))



;; lonely-integer
(defn lonely [v]
  (first (first (filter #(= 1 (second %)) (frequencies v)))))

(defn _lonely []
  (let [n (read-line)]
    (println (lonely (map #(Integer/parseInt %) (clojure.string/split (read-line) #" "))))))


;fibonacci-modified
(defn f 
  "not as effecint as the recursive algo"
  ([]  (f 1 1))
  ([a b] (let [n (+' a (*' b b))]
           (lazy-seq (cons a (f b n))))))

(defn f1 [a b n]
  #_(println a b n)
  (if (= n 1) b (f1 b (+' a (*' b b)) (dec n))))

(defn _f []
  (let [s (read-line) [a b n] (map #(BigInteger. %)  (clojure.string/split s #"\s+")) ]
    #_(println (str (nth (f1 a b) (dec n))))
    (println (str (f1 a b (dec n))))
    ))

(is-funny? "2323" )
(is-funny? "3343")

