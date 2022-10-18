# SAT-Problem Solution using Bio-Inspired Algorithms
## Introduction
The satisfiability problem also known as Boolean Satisfiability Problem is a (yes/no) decision problem, which, given a propositional logic formula, determines whether there is an assignment of the propositional variables that makes the formula satisfiable (true). <br>
SAT occupies a central place in artificial intelligence. The satisfiability of logical formulas, more precisely CNF forms (conjunctive normal forms) of zero order, means that we are on the side of problem solving, automatic reasoning and in any other axis of AI or logic is necessary. <br>
For this project, We are interested in the instances of the [benchmark uf75-325 / uuf75-325](https://www.cs.ubc.ca/~hoos/SATLIB/benchm.html)  defined as follows: <br> <br>
**Instance :** <br>
𝑋= 𝑥1𝑥2𝑥3 ... ... ... ... 𝑥75 :Set of Boolean variables. <br>
𝐶= 𝑐1𝑐2𝑐3 ... ... ... ... 𝑐325 :Set of Clauses where *ci* is a disjunction of literals. <br>
A literal is a Boolean variable with or without the negation connector.<br><br>
**Question:** <br>
Is there an instantiation (a set of Boolean values associated with variables) of X such that the conjunction of the clauses of C is true (SAT)? <br> <br>

For this purpose, we will solve this instance by studying 3 different parts: <br>


> #### Part 01: 
>
> - Using blind methods : the depth-first search (DFS) algorithm.
> - Using heuristic methods: the A* algorithm.<br>.

> #### Part 02: 
>
> - Using Bio Inspired and Evolutionary Methods: The Genetic Algorithm.

> #### Part 03: 
>
> - Using methods from the family of swarm-based algorithms: Ant Colony System (ACS).


## Test
<p> In order to test the implemented algorithms of search in depth first and A* we considered the 100 instances of benchmark UUF75 and we carried out executions of 5 minutes of duration, and for GA and ACS we give the following parameters: </p>
<ul> 
<li> GA : 2500, 25, 50, 50 are the number of ilterations, population size, crossing rate, and mutation rate, respectively. </li>
<li> ACS: 2.1, 0.03, 0.001, 30, 0.001, 70 are the following parameters: alpha, Ro, Beta, Ants Numbers, Q0, and Ilteration number, respectively. </li>
</ul>
 <p> the results obtained are illustrated in the following table: </p>
<table>
  <tr> 
    <th>Algorithm</th>
    <th>Average number of clauses </th>
    <th>Average rate</th>
    <th>Best Number Of SAT Clause</th>
    <th>Worst Number Of SAT Clause</th>
  </tr>
  <tr> 
    <td>DFS</td>
    <td>280</td>
    <td>86.40%</td>
    <td>291</td>
    <td>264</td>
  </tr>
  <tr> 
    <td>A*</td>
    <td>299 </td>
    <td>92%</td>
    <td>311</td>
    <td>284</td>
  </tr>
  <tr> 
    <td>GA</td>
    <td>321 </td>
    <td>98.93%</td>
    <td>325</td>
    <td>317</td>
  </tr>
  <tr> 
    <td>ACS</td>
    <td>302 </td>
    <td>93.14%</td>
    <td>313</td>
    <td>294</td>
  </tr>
  
</table>
<h2>  Conclusion </h2>
 <p> After observing the results of the blind (DFS) and heuristic (A*) methods and those obtained using the genetic algorithm and the ACS, on the given instances of SAT problem, it is clearly observed that the genetic algorithm allows to provide better quality solutions. <p>

<p> In terms of time, GA is clearly much faster than other methods (premature convergence) a large satisfiability rate in less than one minute. <br>

We can clearly see that some instance only satisfies less than 320 clauses and this is because some clauses are difficult to solve or they are not satisfiable. </p>

