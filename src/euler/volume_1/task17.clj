; Topic: elementary

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task17
  (:use clojure.test))

(defn num-letters-count
  "Given N, returns number of letters in all numbers from 1 to N inclusive written in
  words in British English"
  [n]
  (let [digits-teen-len [-3 3 3 5 4 4 3 5 5 4 3 6 6 8 8 7 7 9 8 8]
        tens-len [0 0 6 6 5 5 5 7 6 6]
        and-len 3
        hundred-len 7
        thousand-len 8
        num2len (fn num2len [n and?]
                  (cond
                    (< n 20) (nth digits-teen-len n)
                    (< n 100) (+ (nth (concat [0] (rest digits-teen-len)) (rem n 10))
                                (nth tens-len (quot n 10)))
                    (< n 1000) (+ (nth digits-teen-len (quot n 100)) hundred-len and-len
                                 (num2len (rem n 100) true))
                    (< n 1100) (+ (nth digits-teen-len (quot n 1000)) thousand-len and-len
                                 (num2len (rem n 1000) true))
                    :else (+ (nth digits-teen-len (quot n 1000)) thousand-len
                            (num2len (rem n 1000) true))))]
    (reduce + (map #(num2len % false) (range 1 (inc n))))))

(deftest test1 (is (= (num-letters-count 5) 19)))

(deftest test2 (is (= (num-letters-count 1000) 21124)))

(time (run-tests 'euler.volume_1.task17))