(ns movies.core
  (:gen-class))

(defn zeros
  [n]
  (vec (repeat n 0)))

(defn zeros
  [n]
  (if (zero? n) []
      (conj (zeros (- n 1)) 0)))

(defn zeros
  [n]
  (loop [result [] n n]
    (if (zero? n) result
        (recur (conj result 0) (- n 1)))))

(defn -main
  []
  (println "Hello world!"))
