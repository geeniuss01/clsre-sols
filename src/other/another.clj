(ns another
  (:require [clojure.string :as str]))
(defn error-message
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
       (if (= severity :mild)
         "MILDLY INCONVENIENCED!"
         "DOOOOOOOMED!")))

(defn weird-arity
  ([]
   "Destiny dressed you this morning, my friend, and now Fear is
   trying to pull off your pants. If you give up, if you give in,
   you're gonna end up naked with Fear just standing there laughing
   at your dangling unmentionables! - the Tick")
  ([number]
   (inc number)))


;; about hobits
(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])



(defn getRightPart [part]
  (assoc part :name (str "right-" (get (str/split (:name part) #"-") 1))))

(defn filterlefts [data]
  (filter #(.startsWith (:name %) "left") data))

(defn sym [data]
  (concat data (map getRightPart (filterlefts data))))



;; copied from a book
(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))


(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))

(defn expand-seq [f s]
  "expects function and a sequence"
  (reduce (fn [accu item]
            (into accu (set [item (f item)]))
            ) [] s)
  )

(defn add-missing-spider-leg [part]
  (reduce (fn [n1 n2]
            (into n1 (hash-map :name (str "leg" n2) :size (:size part)))
            )
          [] (range 2 9)
          )
  )

(def spider-parts [{:name "eye" :size 2}
                   {:name "eye1" :size 2}
                   ])



; LCM

(defn any? [l]
  (reduce #(or %1 %2) l))

(defn prime? [n]
  (if (even? n) false
                (let [root (num (int (Math/sqrt n)))]
                  (loop [i 3]
                    (if (> i root) true
                                   (if (zero? (mod n i)) false
                                                         (recur (+ i 2))))))))

(defn n-primes [n]
  (loop [num 2 p []]
    (if (>= (count p) n) p
                         (recur (inc num) (if (prime? num) (concat p [num]) p)))))


(defn primes-below [n]
  (loop [ar [] n1 0]
    (let [ele (get n-primes n1)] (if (> ele n) ar (recur (conj ar ele) (inc n1))))
    )
  )



(do (println (error-message :mild))
    (println "helo")
    (println (primes-below 10))
    )
