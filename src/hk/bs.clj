(ns hk.bs)

;; build-a-string
(defn substitution-cost [x y costx b]
    (let [ss (subs y (count x))]
      #_(println "x" x "y" y "cx" costx "b" b)
      (if (.contains x ss) (+ costx b) -1)))


(defn cost' [t a b costs]
  (let [appnd-cost (+ a (costs (subs t 0 (dec (count t))) 0))
        before-strs (map #(subs t 0 %) (range 1 (count t)))
        subs-costs (map #(substitution-cost %  t (costs %) b) before-strs)]
    #_(println "apc" appnd-cost "bfs" before-strs "sub-c" subs-costs)
    (apply min (conj (filter pos? subs-costs) appnd-cost))
    ))

(defn subss [t a b]
  (loop [n 1 c (inc (count t)) st {}]
    #_(println n c st)
    (if (= c n) st
                (let [pff (subs t 0 n)]
                  #_(println pff)
                  (recur (inc n) c (assoc st pff (cost' pff a b st) ))))))

(defn -m []
  (let  [ T (Integer/parseInt (read-line))]
         (dotimes [t T]
           (let [[n a b] (map #(Integer/parseInt %) (clojure.string/split (read-line) #"\s+")) s (read-line)]
             (println ((subss s a b) s) )))))

;(-m )
