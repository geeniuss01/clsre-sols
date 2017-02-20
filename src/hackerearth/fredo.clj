(ns hackerearth.fredo)
;; https://www.hackerearth.com/challenge/test/programming-practice-challenge/algorithm/f24b8aad15814508a74068398e816dc4/

(def x 5)
(def m 3)
(def k 25)

(defn nxt-exp [n1 n m]
  "retuns next (n^k)%m given (n^k-1)%m"
  (if (zero? n1) (rem n m)
                 (rem (* (rem n1 m) (rem n m)) m))
  )

(def z1 (iterate #(nxt-exp % x m) (rem x m)))

