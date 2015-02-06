(require '(java.io.FileReader))
(require '(java.io.BufferedReader))

(defn make-video-stream
  [x]
  (line-seq (new java.io.BufferedReader
                 (new java.io.FileReader x))))

(defn make-video-array
  "make an array from string of form 'Title (Year)'"
  [x]
  (clojure.string/split x #"[\(\)]"))

(defn make-video-map
  "from [TITLE YEAR] make {:title TITLE :year YEAR}"
  [x]
  {:title (get x 0) :year (get x 1)})

(defn make-videos
  [file]
  (let [vids (make-video-stream file)]
    (map make-video-map (map make-video-array vids))))
