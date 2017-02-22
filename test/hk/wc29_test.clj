(ns hk.wc29_test(:require [expectations :refer :all] [hk.wc29 :refer :all]))

(expect 1 1)
(expect "13.09.2017" (day256 "2017"))
(expect "12.09.2016" (day256 "2016"))
(expect "26.09.1918" (day256 "1918"))

(rem 1700 4)

(expect true (is-leap-russia? 1700))
(expect false (is-leap-russia? 7001))
(expect false (is-leap-russia? 2700))
(expect true (is-leap-russia? 2400))
(expect false (is-leap-russia? 2500))
(expect true (is-leap-russia? 2000))
(expect false (is-leap-russia? 2100))