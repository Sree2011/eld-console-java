<script src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script> 
<script type="module">
    Array.from(document.getElementsByClassName("language-mermaid")).forEach(el => {
      el.classList.add("mermaid");
    });
    import mermaid from 'https://cdn.jsdelivr.net/npm/mermaid@11.4.1/dist/mermaid.esm.min.mjs';
    mermaid.initialize({ startOnLoad: true, theme: 'light' });
  </script>

# ⚡ ELD Console Simulator {#mainpage}

Welcome to the documentation for the Economic Load Dispatch (ELD) Java console app.  
This tool models generator cost functions and dispatch logic for power systems.

## Equations
Power at each generator,
> $$
P_i = \frac{lambda - b_i}{2*c_i}
$$

Total generated power,
> $$
\sum_{i=1}^{n} P_i = P_{\text{total}}
$$

Main condition to be true for lambda iteration to end:
> $$
P_{\text{total}} + P_{\text{tolerance}} >= P_{\text{demand}}
$$
or

> no.of iterations > max_iterations


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
        + ArrayList<Generator> loadFromCSV(String filepath)
    }
    
    class ELDCalculator {
        - float lambda
        - Generator[] genArray
        - float tot_demand
        - float tolerance
        - int max_iterations
        + float[] lambdaIteration()
        + float sum(float[] array)
    }

    class Main{
        + static void main(String[] args)
    }



    Generator <|-- ELDCalculator : uses
    Generator <|-- InputLoader : uses
    ELDCalculator <|-- Main : uses
    InputLoader <|-- Main : uses
    Generator <|-- Main : uses
end
```



## Documentation
[View code documentation](./docs/html/classes.html)


## References
1. [IEEE 10-GENERATOR 39-BUS SYSTEM](https://www.researchgate.net/file.PostFileLoader.html?id=55019916f079ed153f8b4598&assetKey=AS:273740330405917@1442276188879)
2. Saadat, Power System Analysis, 3rd ed. Maple Valley, WA: PSA Publishing, 2010.