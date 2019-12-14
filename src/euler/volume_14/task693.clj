; Topic: number theory
; TODO: in progress

(ns
  ^{:author "raptor_MVK"}
  euler.volume_14.task693
  (:use clojure.test))

(defn max-sequence-length
  ""
  [n]
  (let [len-func (fn [x y]
                   (loop [i x
                          cur y]
                     (if (< cur 2)
                       (inc (- i x))
                       (recur (inc i) (rem (* cur cur) i)))))
        g (fn [x] (apply max (pmap #(len-func x %) (range 1 (inc x)))))
        h (fn [y] (apply max (pmap #(len-func % y) (range y (inc n)))))]
    (apply max (pmap g (range 2 (inc n))))
    ;(apply max (pmap h (range 2 (inc n))))
    ))

(deftest test1 (is (= (max-sequence-length 5) 29)))

(deftest test2 (is (= (max-sequence-length 100) 145)))

;(deftest test3 (is (= (max-sequence-length 10000) 8824)))

(time (run-tests 'euler.volume_14.task693))
