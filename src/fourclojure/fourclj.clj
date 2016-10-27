(ns fourclojure.fourclj
  (require [clojure.set]))

(def from-roman
  (fn [s]
    "takes a roman string and returns decimal numeric value"
    (let [[fs & nx] (clojure.string/reverse s) rm {\I 1 \V 5 \X 10 \L 50 \C 100 \D 500 \M 1000}]
      (second (reduce (fn [[p n] cur_item]
                        (let [prev_v (rm p) cur_v (rm cur_item)]
                          (vector cur_item (+ n (if (< cur_v prev_v) (* -1 cur_v) cur_v))))) [fs (rm fs)] nx))
      )))

(def to-roman
  (fn [num]
    "converts number to roman str"
    (second (let [mp (array-map 1000 \M 900 "CM" 500 \D 400 "CD" 100 "C" 90 "XC" 50 "L" 40 "XL" 10 \X 9 "IX" 5 \V 4 "IV" 1 \I)]
              (reduce (fn [[dvdnd s] dvsrmap]
                        (let [dvsr (first dvsrmap) dvsr_sym (second dvsrmap) qu (quot dvdnd dvsr) rm (rem dvdnd dvsr)]
                          (if (> qu 0) [rm (str s (apply str (repeat qu dvsr_sym)))] [dvdnd s])
                          )) [num ""] mp)
              ))))

(defn test-roman []
  (println "enter roman")
  (println (from-roman (read-line)))
  (println "enter decimal")
  (println (to-roman (bigint (read-line))))
  )



;Write a function which finds all the anagrams in a vector of words. A word x is an anagram of word y if all the letters in x can be rearranged in a different order to form y. Your function should return a set of sets, where each sub-set is a group of words which are anagrams of each other. Each sub-set should have at least two words. Words without any anagrams should not be included in the result.
;test not run
;
;(= (__ ["meat" "mat" "team" "mate" "eat"])
;   #{#{"meat" "team" "mate"}})
;test not run
;
;(= (__ ["veer" "lake" "item" "kale" "mite" "ever"])
;   #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}})

(def list-anagarm (fn [ar]
                    "returns set of sets of words that are anagrams"
                    (reduce (fn [buildup n]
                              (let [v (second n)] (if (> (count v) 1) (conj buildup v) buildup))) #{}
                            (reduce (fn [mp n] (let [ky (into #{} n)]
                                                 (if (mp ky) (assoc mp ky (conj (mp ky) n)) (assoc mp ky #{n})))) {} ar))
                    ))

(defn test-anagram []
  (println (list-anagarm ["meat" "mat" "team" "mate" "eat"]))
  (println (= (list-anagarm ["veer" "lake" "item" "kale" "mite" "ever"])
              #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}}))
  )
