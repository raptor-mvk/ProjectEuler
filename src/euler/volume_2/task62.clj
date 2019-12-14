; Topic: elementary

(ns
  ^{:author "raptor_MVK"}
  euler.volume_2.task62
  (:use clojure.test)
  (:use tools.math)
  (:use tools.conversions))

(defn min-cube-n-permut
  "Given N, returns the smallest cube for which exactly N permutations of its digits are
  cube"
  [n]
  (let [step 1000
        make-cubes (fn [a b] (map #(nat-pow % 3) (range a b)))]
    (loop [cur-max step
           cubes (make-cubes 1 cur-max)]
      (let [permut-groups (group-by #(sort (num2seq %)) cubes)
            n-permuts (filter #(= n (count (last %))) permut-groups)
            next-max (+ cur-max step)]
        (if (empty? n-permuts)
          (recur next-max (concat cubes (make-cubes cur-max next-max)))
          (apply min (flatten (map last n-permuts))))))))

(deftest test1 (is (= (min-cube-n-permut 3) 41063625)))

(deftest test2 (is (= (min-cube-n-permut 5) 127035954683)))

(time (run-tests 'euler.volume_2.task62))
