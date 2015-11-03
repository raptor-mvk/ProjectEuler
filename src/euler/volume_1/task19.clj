; Topic: elementary

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task19
  (:use clojure.test)
  (:use tools.date))

(defn first-sunday-count
  "Returns number of Sundays fell on the first of the month during the twentieth century"
  []
  (let [make-dates (fn [year] (map #(range (days-in-month % year)) (range 12)))]
    (count (filter #(= % 0)
             (take-nth 7 (drop 363 (flatten (map make-dates (range 1900 2001)))))))))

(deftest test1 (is (= (first-sunday-count) 171)))

(time (run-tests 'euler.volume_1.task19))