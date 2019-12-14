; Topic: elementary
; Idea: full search

(ns
  ^{:author "raptor_MVK"}
  euler.volume_2.task68
  (:use clojure.set)
  (:use clojure.test)
  (:use tools.core)
  (:use tools.comb))

(defn max-5-gon-ring
  "Returns maximum 16-digit string for a 5-gon ring, where string formed starting from
  the group of three with the numerically lowest external node and going clockwise"
  []
  (let [ring-rays [[5 4 0] [6 0 1] [7 1 2] [8 2 3] [9 3 4]]
        get-solution (fn [coll]
                       (let [outer-nums-set (clojure.set/difference (set (rrange+ 10))
                                              (set coll))
                             rotated-coll (rswitch 1 coll)
                             ray-sum (/ (+ 55 (reduce + coll)) 5)
                             outer-nums (map #(- ray-sum %1 %2) coll rotated-coll)
                             rotate-pos (min-index outer-nums)]
                         (when (= (set outer-nums) outer-nums-set)
                           (concat (switch rotate-pos coll)
                             (switch rotate-pos outer-nums)))))
        solutions (keep identity (map get-solution
                                   (all-part-perms 5 [1 2 3 4 5 6 7 8 9 10])))
        num-by-rays (fn [coll] (map #(get-by-indices coll %) ring-rays))
        reduce-rays (fn [coll] (map #(reduce + %) coll))
        solutions-by-rays (map num-by-rays solutions)]
    (apply max (map #(Long/parseLong (first %))
                 (filter #(and (apply = (last %)) (= 16 (count (first %))))
                   (map #(vector (apply str (flatten %)) (reduce-rays %))
                     solutions-by-rays))))))

(deftest test1 (is (= (max-5-gon-ring) 6531031914842725)))

(time (run-tests 'euler.volume_2.task68))
