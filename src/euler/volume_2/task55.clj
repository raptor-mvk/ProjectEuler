; Topic: elementary

(ns
  ^{:author raptor_MVK}
  euler.volume_2.task55
  (:use tools.core)
  (:use clojure.test))

(defn potent-lychrel-nums-count
  "Returns the number of potential Lychrel numbers (50 iterations do not
  produce palindrome), which are below 10000"
  []
  (let [iter-count 50
        n 10000
        reverse (fn [n]
                  (loop [cur n
                         res 0]
                    (if (= cur 0)
                      res
                      (recur (quot cur 10) (+' (*' res 10) (rem cur 10))))))
        lychrel? (fn [n k]
                   (loop [cur (+' n (reverse n))
                          i 0]
                     (if (= i k)
                       true
                       (let [new-cur (reverse cur)]
                         (if (= cur new-cur)
                           false
                           (recur (+' cur new-cur) (inc i)))))))]
    (count (filter true? (map #(lychrel? % iter-count) (rrange n))))))

(deftest test1 (is (= (potent-lychrel-nums-count) 249)))

(time (run-tests 'euler.volume_2.task55))
