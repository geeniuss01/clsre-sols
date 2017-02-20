(ns other.in_action)

(println "hello")
(defmulti multi-method-funuc :f)
(defmethod multi-method-funuc 1 [q]
  (println "pfff" (q :a)))
(defmethod multi-method-funuc 2 [q]
  (println "lisp" (:a q)))
(defmethod multi-method-funuc 3 [q]
  (println "yay" q))

(def mp1 {:f 1 :a "o"})
(def mp2 {:f 2 :a "o1"})
(def mp3 {:f 3 :a "o2"})

(multi-method-funuc mp1)
(multi-method-funuc mp2)

(multi-method-funuc mp3)

