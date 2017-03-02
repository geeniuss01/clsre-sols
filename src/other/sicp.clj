(ns other.sicp)


(defn f-1-11-rec [n]
  (cond
    (< n 3) n
    :else (+ (f-1-11-rec (- n 1)) (* 2 (f-1-11-rec (- n 2))) (* 3 (f-1-11-rec (- n 3))))))

(defn f-1-11-iter
  ([n]
   (cond (< n 3) n :else  (f-1-11-iter 0 1 2 (- n 2))))
  ([a b c n]
   (cond
     (zero? n) c
     :else (f-1-11-iter b c (+ c (* 2 b) (* 3 a)) (dec n)))))


(rem (Math/pow 2 7) 7)
(defn f-1-12-rec [a b]
  (cond
    (< a b) "invalid"
    (zero? b) 1
    (= a b) 1
    :else (+ (f-1-12-rec (dec a) (dec b)) (f-1-12-rec (dec a) b))))

#_(f-1-12-rec 0 0)

#_(->> [1 2 9]
    (map inc)   
    (vec ))

(+ 2 (+ 3 (+ 4 (+ 5 0))))
