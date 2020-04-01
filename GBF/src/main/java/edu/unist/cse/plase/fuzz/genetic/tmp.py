# File: guessPasswordTests.py
#    from chapter 1 of _Genetic Algorithms with Python_
#
# Author: Clinton Sheppard <fluentcoder@gmail.com>
# Copyright (c) 2016 Clinton Sheppard
#
# Licensed under the Apache License, Version 2.0 (the "License").
# You may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
# implied.  See the License for the specific language governing
# permissions and limitations under the License.

import datetime
import random
import unittest
import sys
import os

import geneticalgorithm_simple as ge
import GetDistance as gd
import Grammarfuzzing as gf
from functools import partial

def _get_fitness(dout_path,program,input):
    return gd.get_distance(dout_path,program,input)

# TODO
def display(candidate, startTime):
    timeDiff = datetime.datetime.now() - startTime
    print("{}\t{}\t{}".format(
        candidate.Genes, candidate.Fitness, timeDiff))

def create_gene(index, gates, sources):
    if index < len(sources):
        gateType = sources[index]
    else:
        gateType = random.choice(gates)
    indexA = indexB = None
    if gateType[1].input_count() > 0:
        indexA = random.randint(0, index)
    if gateType[1].input_count() > 1:
        indexB = random.randint(0, index) \
            if index > 1 and index >= len(sources) else 0
        if indexB == indexA:
            indexB = random.randint(0, index)
    return Node(gateType[0], indexA, indexB)

def mutate(childGenes, fnCreateGene, fnGetFitness, sourceCount):
    count = random.randint(1, 5)
    initialFitness = fnGetFitness(childGenes)
    while count > 0:
        count -= 1
        indexesUsed = [i for i in nodes_to_circuit(childGenes)[1]
                       if i >= sourceCount]
        if len(indexesUsed) == 0:
            return
        index = random.choice(indexesUsed)
        childGenes[index] = fnCreateGene(index)
        if fnGetFitness(childGenes) > initialFitness:
            return

class Fuzzing(unittest.TestCase):
    geneset = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!.,"

    def guess_password(self, target):
        startTime = datetime.datetime.now()

        def fnDisplay(candidate, length=None):
            if length is not None:
                print("-- distinct nodes in circuit:",
                      len(nodes_to_circuit(candidate.Genes)[1]))
            display(candidate, startTime)

        def fnGetFitness(genes):
            return get_fitness(genes)

        def fnCreateGene(index):
            return create_gene(index, self.gates, self.sources)

        def fnMutate(genes):
            mutate(genes, fnCreateGene, fnGetFitness, len(self.sources))

        optimalFitness = len(target)
        best = genetic.get_best(fnGetFitness, len(target), optimalFitness,
                                self.geneset, fnDisplay)
        self.assertEqual(best.Genes, target)

    def test_Random(self):
        length = 150
        target = ''.join(random.choice(self.geneset)
                         for _ in range(length))

        self.guess_password(target)

    def test_benchmark(self):
        genetic.Benchmark.run(self.test_Random)


if __name__ == '__main__':
    if(len(sys.argv)<1):
        exit()
    dout_path = os.path.abspath(os.getcwd())+"/dout"
    print(dout_path)
    program_path = sys.argv[1]
    get_fitness = partial(_get_fitness,dout_path,program_path)
    while(True):
        print(get_fitness(gf.simple_grammar_generator(grammar=gf.EXPR_GRAMMAR, max_nonterminals=10, log=False,config_file_path=os.path.dirname(os.path.abspath(__file__))+'/log/config')))

