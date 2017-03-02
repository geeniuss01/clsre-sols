(ns hk.star
  (:require [clojure.math.combinatorics :refer :all] [lacij.edit.graph :refer :all] [lacij.view.graphview :refer :all] [lacij.layouts.core :refer :all])
  )


(defn rd []
  (Double/parseDouble (read-line)))

(def arth (fn []
            (let [meal (rd) tip (rd) tax (rd)]
              (println "The total meal cost is" (Math/round (+ meal (/ (* meal tip) 100) (/ (* meal tax) 100))) "dollars."))))


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


(def ip (fn [] (let [N (Integer/parseInt (read-line))]
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
  ([] (f 1 1))
  ([a b] (let [n (+' a (*' b b))]
           (lazy-seq (cons a (f b n))))))

(defn f1 [a b n]
  #_(println a b n)
  (if (= n 1) b (f1 b (+' a (*' b b)) (dec n))))

(defn _f []
  (let [s (read-line) [a b n] (map #(BigInteger. %) (clojure.string/split s #"\s+"))]
    #_(println (str (nth (f1 a b) (dec n))))
    (println (str (f1 a b (dec n))))))



;; taum-and-bday

(defn taum [b w x y z]
  (let [x1 (+ y z) y1 (+ x z)]
    (+ (* b (min x x1)) (* w (min y y1)))))


;;sherlock-and-the-beast

(defn beast
  ([n] (beast (* 3 (quot n 3)) (rem n 3)))
  ([a' b']
   (loop [a a' b b']
     #(println a b)
     (cond
       (or (< a 0) (< b 0)) (str -1)
       (and (zero? (rem a 3)) (zero? (rem b 5))) (str (clojure.string/join (repeat a 5)) (clojure.string/join (repeat b 3)))
       :else (recur (- a 3) (+ b 3))))))





;;challenges/jumping-on-the-clouds


(defn count-jumps [s]
  (loop [cnt 0 s' s]
    #_(println cnt s')
    (cond
      (empty? s') cnt
      (= s' "0") cnt
      (or (= s' "00") (= s' "10")) (inc cnt)
      (= \0 (get s' 2)) (recur (inc cnt) (subs s' 2))
      (= \1 (get s' 2)) (recur (inc cnt) (subs s' 1)))))



#_(let [n (read-line)]
    (println (count-jumps (.replace (read-line) " " ""))))

;; challenges/journey-to-the-moon


(defn journey-combs [v']
  (loop [v (vals (frequencies v')) c []]
    (if (empty? v) (apply + (flatten c))
                   (recur (next v) (conj c (map #(* (first v) %) (next v)))))))

(defn -j1 []
  (let [[n p] (map #(bigint %) (clojure.string/split (read-line) #"\s+"))]
    (loop [i 0 v (vec (range n))]
      (if (>= i p) (println (journey-combs v))
                   (let [n (map #(bigint %) (clojure.string/split (read-line) #"\s+"))
                         a (apply min n) b (apply max n)]
                     #_(println i n a b  v (get v b) (get v a ) )
                     (recur (inc i) (replace {(get v b) (get v a)} v)))))))


;; app-refactor

#_(defn to-paths [p k v]
  (if (set? k)
    (map #(to-paths p % v) k)
    (str "(" p ")[" k "]->(" v ")")))

(def view-states {:FPL "full page loader" :FPE "full page error"
                  :LST "list with data" :LSTSCR "list with scroll"
                  :LSTPRG "list with progress" :LSTSCRPRG "list with scroll and progress"
                  })

(def data {:CR "cache response" :CE "cache error" :NR "network response" :NE "network error"})

(def all-comb (cartesian-product (keys view-states) (keys data)))

(def mapping {:FPL {#{:CR :NR} :LST #{:CE :NE} :FPE}
              :FPE {#{:CR :NR} :LST #{:CE :NE} :FPE}
              :LST {:CR-ignore :LST :NR-replace :LST }

              })

(defn to-paths-comp [p k v]
  (if (set? k)
    (to-paths-comp p (clojure.string/join " " k) v)
    (str "(" p ")[" k "]->(" v ")")))

(defn pff [v]
  (println (clojure.string/join "," v)))

(defn red-one [k1 v1]
  (reduce #(let [k (first %2) v (second %2)]
            (conj %1 (to-paths-comp k1 k v))) [] v1))
(defn abb [mp]
  (reduce #(apply conj %1 (apply red-one %2)) [] mp))

#_(-> mapping
    (abb)
    (pff))

 ;; testing https://yuml.me/edit/4bcc7bee

#_(let [g (-> (graph :width 400 :height 400)
              (add-node :hermes "Hermes")
              (add-node :zeus "Zeus")
              (add-node :ares "Ares")
              (add-edge :father1 :hermes :zeus "something else")
              (add-edge :father2 :ares :zeus)
              (lacij.layouts.core/Layout  :hierarchy)
              (build))]
    (export g "/tmp/simple.svg" :indent "yes"))

