(ns
  ^{:author raptor_MVK}
  tools.seqs)

(defn fib-seq
  "Returns lazy sequence of Fibonnaci numbers"
  [] (let [next-fib (fn [[prev cur]] [cur (+ prev cur)])]
       (map first (iterate next-fib [1 1]))))

