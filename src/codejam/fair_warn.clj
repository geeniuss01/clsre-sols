;;http://code.google.com/codejam/contest/433101/dashboard#s=p1

(ns codejam.fair_warn)


;; euclid algo taken from here http://rosettacode.org/wiki/Greatest_common_divisor#Euclid.27s_Algorithm
(defn gcd 
  "(gcd a b) computes the greatest common divisor of a and b."
  [a b]
  (if (zero? b)
    a
    (recur b (mod a b))))
(defn gcd*
  "greatest common divisor of a list of numbers"
  [& lst]
  (reduce gcd
    lst))

(gcd* 26 11 6)
(gcd* 30 15 10)


















(def biga 800000000000000000001N)
(def bigb 900000000000000000001N)
(def tdiff 99999999999999999999)
(gcd  biga bigb)
(gcd (+ biga tdiff) (+ bigb tdiff))



