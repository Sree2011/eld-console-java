<script src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script> 
<script type="module"> import mermaid from 'https://cdn.jsdelivr.net/npm/mermaid@11.4/dist/mermaid.esm.min.mjs'; mermaid.initialize({ startOnLoad: true, theme: 'light' }); </script>

# ⚡ ELD Console Simulator {#mainpage}

Welcome to the documentation for the Economic Load Dispatch (ELD) Java console app.  
This tool models generator cost functions and dispatch logic for power systems.

## Equations

$$
P_i = \frac{lambda - b_i}{2*c_i}
$$

## Class diagram

```mermaid
classDiagram
    class Generator {
        - int gen_id
        - float min_capacity
        - float max_capacity
        - float a
        - float b
        - float c
        + float validatePower(float power)
        + float getA()
        + void setA(float a)
        + float getB()
        + void setB(float b)
        + float getC()
        + void setC(float c)
    }
    
    class InputLoader{
        + ArrayList<Generator> loadFromUser()
    }
    
    class ELDCalculator {
        - float lambda
        - Generator[] genArray
        - float tot_demand
        - float tolerance
        - int max_iterations
        + float[] lambdaIteration()
    }

    Generator <|-- ELDCalculator : uses
    Generator <|-- InputLoader : uses
```


## Documentation
[View code documentation](./docs/html/index.html)


