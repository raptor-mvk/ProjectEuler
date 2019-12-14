; Topic: number theory

(ns
  ^{:author "raptor_MVK"}
  euler.volume_1.task14
  (:use clojure.test)
  (:use tools.seqs))

(defn max-collatz-seq
  "Given N, returns starting number, under N, produces the longest Collatz sequence"
  [n]
  (let [collatz-len (fn collatz-len [n lens]
                       (let [len (get-in lens [n])]
                         (if (nil? len)
                           (let [new-n (if (odd? n)
                                         (inc (*' n 3))
                                         (quot n 2))
                                 new-lens (collatz-len new-n lens)]
                             (assoc new-lens n (inc (get-in new-lens [new-n]))))
                           (assoc lens n len))))]
    (loop [ns (range 2 n)
          lens {1 1}]
      (if (empty? ns)
        (key (apply max-key val lens))
        (recur (rest ns) (collatz-len (first ns) lens))))))

(deftest test1 (is (= (max-collatz-seq 1000000) 837799)))

(time (run-tests 'euler.volume_1.task14))