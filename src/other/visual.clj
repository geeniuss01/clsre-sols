(ns other.visual
  (:use clojure.pprint
        lacij.layouts.layout
        lacij.model.graph
        lacij.edit.graph
        lacij.view.graphview))


;; view-datasource-states
;(def cache-mapping {:FPL         ["full page loader" {:CR :LST-CH :NR :LST-NW #{:CE :NE} :FPE} "no ptr"]
;                    :FPE         ["full page error" {:CR :LST-CH :NR :LST-NW} "no ptr, retry"]
;                    :LST-CH      ["list with cache data" {:NR :LST-NW :NR :LST-CH-MORE :NR :LST-NW}
;                                  "ignore cache, no  ptr, shows loader and updating header. user scroll considered. hasDataChanged"]
;                    :LST-NW      ["list with network data" {:PL* :LST-CH} "ignore cache, enable ptr "]
;                    :LST-CH-MORE ["cache list with more news" {:MC* :LST-NW :PL* :LST-CH}]
;                    })

;
;(def nocache-mapping {:FPL    ["full page loader" {:NR :LST-NW :NE :FPE} "no ptr"]
;                      :FPE    ["full page error" {:NR :LST-NW} "no ptr, retry"]
;                      :LST-NW ["list with network data" {:PL* :FPL} "no ptr "]
;                      })

;
;(def data-arrival {
;                   :CR "cache response"
;                   :CE "cache error"
;                   :NR "network response"
;                   :NE "network error"})


(def view-states {
                  :FPL         ["full page loader"]
                  :FPE         ["full page error"]
                  :LST-CH      ["list with cache data"]
                  :LST-NW      ["list with network data"]
                  :LST-CH-MORE ["cache list with more news"]
                  })

(def events {
             :CR  "cache response"
             :CE  "cache error"
             :NR  "network response"
             :NE  "network error"
             :MC* "more news click"
             :PL* "pull to refresh"
             :SC* "scroll for next page"
             })

(def fp-mapping [
                 [:FPL :CR :LST-CH]
                 [:FPL :NR :LST-NW]
                 [:FPL :CE :FPE]
                 [:FPL :NE :FPE]

                 [:FPE :CR :LST-CH]
                 [:FPE :NR :LST-NW]

                 [:LST-CH :NR :LST-NW "replace, hasDataChanged"]
                 [:LST-CH :NR :LST-CH-MORE]
                 [:LST-CH :NR :LST-NW "no replace, !hasDataChanged"]
                 [:LST-CH :NE :LST-CH "error toast"]

                 [:LST-NW :PL* :LST-CH]

                 [:LST-CH-MORE :MC* :LST-NW "replace"]
                 [:LST-CH-MORE :PL* :LST-CH]
                 ])

(def np-action-mapping [
                     [:SC* :LST-CH :CH_ONLY]
                     [:SC* :LST-CH-MORE :CH_ONLY]
                     [:SC* :LST-NW :CH_NW]
                     ])

(def fp-mapping-nocache [
                         [:FPL :NR :LST-NW]
                         [:FPL :NE :FPE]

                         [:FPE :NR :LST-NW]

                         [:LST-NW :PL* :FPL]

                         ])
(defn ad-n [g1 sq]
  (loop [g g1 i sq]
    (if (empty? i) g
                   (recur (add-node g (first i) :width 300) (next i)))))

(defn ad-e [g1 ssq]
  (loop [g g1 sq ssq]
    (if (empty? sq) g
                    (let [[f pth t] (first sq)]
                      #_(println "f=" f "p=" pth "to=" t)
                      (recur (add-edge g (geneid) f t (str pth) :style {:stroke "darkcyan" :stroke-dasharray "9, 5"}) (next sq))))))

(defn grh [l n]
  (-> (graph)
      (ad-n (set (map first l)))
      (ad-e l)
      (layout :random)
      (build)
      (export n :indent "yes")
      ))

(grh fp-mapping "h1.svg")


;- ------------------ 7                                      ;;7

(defn addd-nodes [g1 m]
  (loop [g g1 i m]
    (if (empty? i) g
                   (let [f (first i) k (first f) v (first (second f))]
                     (recur (add-node g k (str (first f) "\n[" v "]") :width 300) (next i))))))

(defn addd-edges [g1 k v1]
  (loop [g g1 v v1]
    (if (empty? v) g
                   (let [[pth to] (first v)]
                     (recur (add-edge g (geneid) k to (str pth) :style {:stroke "darkcyan" :stroke-dasharray "9, 5"}) (next v))))))

(defn addd-all-edges [g1 mp]
  (reduce #(addd-edges %1 (first %2) (second (second %2))) g1 mp))


(defn bld-grph [mp l]
  (-> (graph)
      (addd-nodes mp)
      (addd-all-edges mp)
      (layout :random)
      (build)
      (export l :indent "yes")
      ))
(defn tst []
  (let [g (-> (graph :width 900 :height 900)
              (add-node :hermes "Hermes")
              (add-node :zeus "Zeus")
              (add-node :ares "Ares")
              (add-edge :father1 :hermes :zeus "something else" :text-anchor "end")
              (add-edge :father2 :ares :zeus)
              (layout :random)
              (build))]
    (export g "simple.svg" :indent "yes")))

;(tst)
;(bld-grph cache-mapping "h.svg")
;(println "OK" (System/currentTimeMillis))

