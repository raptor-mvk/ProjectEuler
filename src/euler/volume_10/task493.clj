; Topic: combinatorics
; Idea: define the probability of C distinct colors as
;       N(k, total, good, C), then N(0, any, any, 0) = 1,
;       N(0, any, any, any > 0) = 0,
;       N(k > 0, total, good, 0) = (good! / (good - k)!) /
;                                  (total! / (total - k!),
;       N(k > 0, total, good, C > 0) = (total - good) / total *
;         N(k - 1, total - 1, good + m - 1, C - 1) + good / total *
;         N(k - 1, total - 1, good - 1, C)

(ns
  ^{:author raptor_MVK}
  euler.volume_10.task493
  (:use tools.math)
  (:use tools.core)
  (:use clojure.test))

(defn expected-distinct-colors
  "Given N, K, M, returns the expected number of distinct colors in K randomly picked
  balls from N balls, where there is M balls for each color"
  [n k m]
  (let [colors-count (quot n m)
        k-good-prob (fn [k total good]
                      (reduce *' (map #(/ %1 %2) (range+ (inc (- good k)) good)
                                   (range+ (inc (- total k)) total))))
        colors-prob (fn colors-prob [k total good colors]
                      (if (= k 0)
                        (if (= colors 0) 1 0)
                        (if (= colors 0)
                          (k-good-prob k total good)
                          (+' (*' (/ (- total good) total)
                                (colors-prob (dec k) (dec total) (+ good (dec m))
                                  (dec colors)))
                            (*' (/ good total)
                              (colors-prob (dec k) (dec total) (dec good) colors))))))]
    (round-to-fixed (reduce + (map #(* (inc %) (colors-prob (dec k) (dec n) (dec m) %))
                                (rrange colors-count))) 9)))

(deftest test1 (is (= (expected-distinct-colors 70 20 10) 6.818741802)))

(time (run-tests 'euler.volume_10.task493))
