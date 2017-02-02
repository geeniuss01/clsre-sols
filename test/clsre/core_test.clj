(ns clsre.core-test
  (:require [clojure.test :refer :all]
            [clsre.core :refer :all])
  (:use 'expectations))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))

(expect nil? nil)
