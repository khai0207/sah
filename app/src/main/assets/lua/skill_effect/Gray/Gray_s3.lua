--v3
--[[
Gray
0
0
penxue
2
{default,qianjin,1,0,0,1,1.000000}{default,gongji4,1,0,2,78,1.000000}
1
{default,-400.000000,0.000000,-400.000000,0.000000,1,1,0,1,0.000000,0,0,0,0}
0

0

1
{0,Gray,2,0,0.000000,0.000000,77,0}
1
{default,texie1,1,0,0,77,1.000000}
1
{default,550.000000,300.000000,550.000000,300.000000,2,2,0,1,0.000000,0,0,0,0}
1
{default,0.800000,0.800000,0.800000,0.800000,0,0,1}
0


daiji
]]--
local skillTest = {
attack_type       = "jingong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"qianjin",1.000000,0,1,1},{"gongji4",1.000000,2,78,1}}, 
pos_sequence      = {{-400.000000,0.000000,-400.000000,0.000000,1,1,0,1,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"Gray",2,0,0.000000,0.000000,{{"texie1",1.000000,0,77,1}},{{550.000000,300.000000,550.000000,300.000000,2,2,0,1,0.000000,0,0,0,0}},{{0.800000,0.800000,0.800000,0.800000,0,0,1}},{},0}}, 
bloodNum          = 8,
flyAnim           = "ladeng",
flyAnim_equence   = {{"impact",1}}, 
par               = "",
fly_v             = 100,
fly_h             = 0,
hurtAnim          = "penxue",
hurtAnim_equence  = {{"impact",1}},
skillCallFunc     = function(self,battleSkill,battleTable) 
   local skillData = battleTable.m_currentFrameData; 
   table.foreach(skillData,function(k,v) 
   end); 
   local hpValue = 1;
   local function attackEnd()
   end
   battleSkill:createNormalAttack({skills_table = self,hpValue = skillData.hurt,animEndCallFunc = attackEnd});
end, 
}
return skillTest
