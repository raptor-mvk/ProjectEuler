(ns
  ^{:author raptor_MVK}
  tools.core)

(declare but-nth n-subseqs)

(defn but-nth
  "Given collection and N, returns collection with nth element removed"
  [coll n]
  (concat (take n coll) (drop (inc n) coll)))

(defn n-subseqs
  "Given sequence and N, returns all subsequences of length N"
  [coll n]
  (partition n (apply interleave (take n (iterate rest coll)))))

