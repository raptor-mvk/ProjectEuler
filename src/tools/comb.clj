(ns
  ^{:author raptor_MVK}
  tools.comb)

(defn pp-count
  "Given N, K, returns number of partial permutations of k elements from n"
  [n k]
  (reduce *' (map #(/ %1 %2)
               (reverse (range (inc (- n k)) (inc n))) (range 1 (inc k)))))

