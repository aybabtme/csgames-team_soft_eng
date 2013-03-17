import sys
import search_engine

def load_sentences(filename):
    with open(filename) as f:
        sentences = f.readlines()
    return [line.strip() for line in sentences]


if __name__ == '__main__':
    sentences = load_sentences(sys.argv[1])
    query = sys.argv[2]
    
    for sentence in sentences:
        if search_engine.is_match(sentence, query):
            print (sentence)
