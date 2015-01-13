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

(defn zeros
  [n]
  (nth (iterate #(conj % 0) []) n))

(zeros 7)

(defn -main
  []
  (println "Hello world!"))

(mapv (fn [v] (* 2 v)) (vec (range 1 9)))
;;=> [2 4 6 8 10 12 14 16]

(reduce-kv (fn [r k v] (conj r [k v])) [] (vec (range 1 9)))
;;=> [[0 1] [1 2] [2 3] [3 4] [4 5] [5 6] [6 7] [7 8]]
