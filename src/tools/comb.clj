(ns
  ^{:author raptor_MVK}
  tools.comb
  (:use tools.math))

(declare perm-gen pp-count)

(defn perm-gen
  "Given N, a sorted collection of digits, returns Nth lexicographical permutation of
  these digits"
  [n coll]
  (if (= 1 (count coll))
    coll
    (let [tail-perm-count (fact (dec (count coll)))
          digit (quot n tail-perm-count)]
      (concat [(nth coll digit)]
        (perm-gen (rem n tail-perm-count)
          (concat (take digit coll) (drop (inc digit) coll)))))))

(defn pp-count
  "Given N, K, returns number of partial permutations of k elements from n"
  [n k]
  (reduce *' (map #(/ %1 %2)
               (reverse (range (inc (- n k)) (inc n))) (range 1 (inc k)))))