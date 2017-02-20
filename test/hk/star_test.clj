(ns hk.star-test
  (:require [expectations :refer :all]
            [hk.star :refer :all]))

(expect 1 1)
(expect [0 1 1 3 3 3] (comb-all 5 [[1 2] [3 4] [3 5]]))
(expect [0 1 1 3 4] (comb [1 2] [0 1 2 3 4]))
(expect 11 (journey-combs [0 1 1 3 3 3]))

(expect [0 1 1 3 3 5 5 7 3 3] (comb-all 10 [[2 1] [4 3 8] [3 9] [6 5]]))