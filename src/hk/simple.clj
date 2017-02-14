(ns hk.simple)



(def p (fn trivial []  (
          let [
               a0_temp (read-line)
               a0_t (clojure.string/split a0_temp #"\s+")
               a0 (Integer/parseInt (a0_t 0))
               a1 (Integer/parseInt (a0_t 1))
               a2 (Integer/parseInt (a0_t 2))
               ]

          (
            let [
                 b0_temp (read-line)
                 b0_t (clojure.string/split b0_temp #"\s+")
                 b0 (Integer/parseInt (b0_t 0))
                 b1 (Integer/parseInt (b0_t 1))
                 b2 (Integer/parseInt (b0_t 2))
                 df [(- a0 b0) (- a1 b1) (- a2 b2)]
                 ]
            (println (count (filter #(> % 0) df)) (count (filter #(< % 0) df)))
            )

          )))

(def num-words-from-camelcase-word (fn [s]
                                     (println (inc (count (filter #(Character/isUpperCase %) s))))))


(num-words-from-camelcase-word "qCameCas")

